package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Board;
import com.boot.reserveproject.domain.BoardWithCommentsCount;
import com.boot.reserveproject.domain.Member;
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
    public void createOrUpdateBoard(Board board){
        boardRepository.save(board);
    }
    public Board findOneBoardByNo(Long no){
        Board board=boardRepository.findOneBoardByNo(no);
        return board;
    }
    public void deleteBoard(long no){
        boardRepository.deleteById(no);
    }
    public Board getOneBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다"));
    }
    public Board getOneBoardByNo(Long no){
        return boardRepository.getOneBoardByNo(no);
    }
    public List<BoardWithCommentsCount> findBoardWithCommentsCountByNo(){
        return boardRepository.findBoardWithCommentsCountByNo();
    }
}
