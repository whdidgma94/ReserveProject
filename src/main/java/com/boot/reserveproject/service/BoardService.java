package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository){this.boardRepository=boardRepository;}
    public List<Board>selectAllBoard(){
        List<Board>boardList=boardRepository.selectAllBoard();
        return boardList;
    }
    public Long getBoardLength(){
        Long length=boardRepository.countBoards();
        return length;
    }
    public Optional<Board> getLastBoard(){
        Optional<Board> board=boardRepository.findBoardByMaxNo();


        return board;
    }
    public void insertBoard(String id, long no, String title, String content,String img ){
        boardRepository.insertBoard(id,no,title,content,img);
    }
}
