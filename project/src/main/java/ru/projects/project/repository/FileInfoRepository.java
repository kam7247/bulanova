package ru.egprojects.sw1_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egprojects.sw1_springboot.model.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findOneByStorageFileName(String storageFileName);
}
