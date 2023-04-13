package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.QnA;
import com.boot.reserveproject.repository.QnARepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnAService {
    private final QnARepository qnARepository;

    public QnAService(QnARepository qnARepository) {
        this.qnARepository = qnARepository;
    }

    public void createQnA(QnA qna){
        qnARepository.save(qna);
    }

    public List<QnA> getReceiveQnA(String receiver){
        return qnARepository.getMyQnA(receiver);
    }
    public List<QnA> getSendQnA(String sender){
        return qnARepository.getMyQnA(sender);
    }
    public QnA getOneQnA(Long id){
        return qnARepository.findById(id).get();
    }
}
