package com.boot.reserveproject.service;

import com.boot.reserveproject.domain.Notice;
import com.boot.reserveproject.repository.NoticeRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public void createNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public List<Notice> getAllNotice() {
        List<Notice> list = noticeRepository.findAll();
        return list;
    }

    @Modifying
    @Transactional
    public void updateNotice(Long id, String subject, String context) {
        noticeRepository.updateNotice(subject, context, id);
    }

    public Notice getOneNotice(Long id) {
        return noticeRepository.findById(id).get();
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }
}
