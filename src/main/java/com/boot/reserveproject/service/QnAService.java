package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.QnA;
import com.boot.reserveproject.repository.QnARepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QnAService {
    private final QnARepository qnARepository;

    public QnAService(QnARepository qnARepository) {
        this.qnARepository = qnARepository;
    }
    public List<QnA> getQuestionList(){
        return qnARepository.getQuestionList();
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

    public QnA getAnswer(Long id){
        return qnARepository.getAnswer(id);
    }

    public String getSender(Long id){
        return qnARepository.getSender(id);
    }
    @Modifying
    @Transactional
    public void updateStatusRead(Long id){
       qnARepository.updateStatusRead(id);
    }
    @Modifying
    @Transactional
    public void updateStatusDone(Long id){
        qnARepository.updateStatusDone(id);
    }
}
