package com.mycompany.white.controller;

import com.mycompany.white.domain.dto.CategoryDto;
import com.mycompany.white.domain.dto.PostDto;
import com.mycompany.white.domain.entity.Category;
import com.mycompany.white.domain.entity.Post;
import com.mycompany.white.service.CategoryService;
import com.mycompany.white.service.PostService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PostController {

    @Value("${image.upload.path}")
    private String uploadPath;

    @Value("${resource.handler}")
    private String resourceHandler;

    private final PostService postService;
    private final CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/admin/post")
    public String post(Model model, @RequestParam(name = "categoryName", required = false) String categoryName){

        if (categoryName == null || categoryName.equals("전체")){
            List<Post> findPosts = postService.findAllPost();
            List<PostDto> postDtos = findPosts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
            model.addAttribute("posts", postDtos);
        }
        else{
            List<Post> findPosts = postService.findPostByCategoryName(categoryName);
            List<PostDto> postDtos = findPosts.stream().map(p -> new PostDto(p)).collect(Collectors.toList());
            model.addAttribute("posts", postDtos);
        }

        List<Category> findCategories = categoryService.findAllCategory();
        List<CategoryDto> categoryDtos = findCategories.stream().map(c -> new CategoryDto(c)).collect(Collectors.toList());

        model.addAttribute("categories", categoryDtos);

        return "admin/post/adminPostList";
    }

    @GetMapping("/admin/post/new")
    public String postForm(Model model){
        List<Category> findCategories = categoryService.findAllCategory();

        model.addAttribute("categories", findCategories);
        model.addAttribute("postForm", new PostDto());

        return "admin/post/adminPostForm";
    }

    @PostMapping("/admin/post/imageUpload")
    public void postImage(MultipartFile upload, HttpServletResponse res, HttpServletRequest req){

        OutputStream out = null;
        PrintWriter printWriter = null;

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html;charset=utf-8");

        try{
            UUID uuid = UUID.randomUUID();
            String extension = FilenameUtils.getExtension(upload.getOriginalFilename());

            byte[] bytes = upload.getBytes();

            // 실제 이미지 저장 경로
            String imgUploadPath = uploadPath + File.separator + uuid + "." + extension;

            // 이미지 저장
            out = new FileOutputStream(imgUploadPath);
            out.write(bytes);
            out.flush();

            // ckEditor 로 전송
            printWriter = res.getWriter();
            String callback = req.getParameter("CKEditorFuncNum");
            String fileUrl = "/admin/post/image/" + uuid + "." + extension;

            printWriter.println("<script type='text/javascript'>"
                    + "window.parent.CKEDITOR.tools.callFunction("
                    + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
                    +"</script>");

            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) { out.close(); }
                if(printWriter != null) { printWriter.close(); }
            } catch(IOException e) { e.printStackTrace(); }
        }
    }

    @PostMapping("/admin/post/new")
    public String savePost(PostDto postDto, @RequestParam Long categoryId){
        Category category = categoryService.findCategory(categoryId);

        Post.createPost(postDto.getTitle(), postDto.getContent(), category);

        postService.savePost(categoryId, postDto.getTitle(), postDto.getContent());
        return "redirect:/admin/post";
    }

    @GetMapping("/post/{postId}")
    public String postDetail(@PathVariable Long postId, Model model){
        Post findPost = postService.findPost(postId);
        PostDto postDto = modelMapper.map(findPost, PostDto.class);
        model.addAttribute("post", postDto);
        return "admin/post/adminPostDetail";
    }

    @GetMapping("/admin/post/update/{postId}")
    public String updatePostForm(@PathVariable Long postId, Model model){
        Post findPost = postService.findPost(postId);
        PostDto postDto = modelMapper.map(findPost, PostDto.class);
        List<Category> findCategories = categoryService.findAllCategory();

        model.addAttribute("categories", findCategories);
        model.addAttribute("postForm", postDto);
        return "admin/post/adminPostUpdateForm";
    }

    @PostMapping("/admin/post/update/{postId}")
    public String updatePost(@PathVariable Long postId, PostDto postDto, @RequestParam Long categoryId){
        Category category = categoryService.findCategory(categoryId);
        postService.updatePost(postId, postDto, category);
        return "redirect:/admin/post";
    }

    @GetMapping("/admin/post/delete/{postId}")
    public String deletePost(@PathVariable Long postId){
        Post post = postService.findPost(postId);
        postService.deletePost(post);
        return "redirect:/admin/post";
    }

}
