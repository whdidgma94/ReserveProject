package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public void createNotice(Notice notice){
        noticeRepository.save(notice);
    }

    public List<Notice> getAllNotice(){
        List<Notice> list = noticeRepository.findAll();
        if (list.isEmpty()) throw new IllegalStateException("데이터가 존재하지않습니다");
        System.out.println("list = " + list.get(0));
        return list;
    }

    public Notice getOneNotice(Long id){
        return noticeRepository.findById(id).get();
    }
    public void deleteNotice(Long id){
        noticeRepository.deleteById(id);
    }
}
