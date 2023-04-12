package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private NoticeRepository noticeRepository;

    public void createNotice(Notice notice){
        noticeRepository.save(notice);
    }

    public List<Notice> getAllNotice(){
        return noticeRepository.findAll();
    }
}
