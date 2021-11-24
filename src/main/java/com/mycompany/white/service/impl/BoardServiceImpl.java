package com.mycompany.white.service.impl;

import com.mycompany.white.domain.entity.Board;
import com.mycompany.white.repository.BoardRepository;
import com.mycompany.white.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void createBoard(Board board) {
        boardRepository.save(board);
    }
}
