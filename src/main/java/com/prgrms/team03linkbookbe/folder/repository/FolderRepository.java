package com.prgrms.team03linkbookbe.folder.repository;

import com.prgrms.team03linkbookbe.folder.entity.Folder;
import com.prgrms.team03linkbookbe.user.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    @Query(value = "SELECT DISTINCT f FROM Folder f LEFT JOIN FETCH f.folderTags WHERE f.user = :user and f.isPrivate = :isPrivate",
            countQuery = "SELECT count(f) FROM Folder f")
    Page<Folder> findAllByUser(User user, Boolean isPrivate, Pageable pageable);

    @Query(value = "SELECT DISTINCT f FROM Folder f LEFT JOIN FETCH f.folderTags WHERE f.isPrivate = :isPrivate",
            countQuery = "SELECT count(f) FROM Folder f")
    Page<Folder> findAll(@Param("isPrivate") Boolean isPrivate, @Param("pageable") Pageable pageable);

    @Query("SELECT DISTINCT f FROM Folder f LEFT JOIN f." +
            "bookmarks LEFT JOIN f.folderTags JOIN  f.user WHERE f.id = :folderId")
    List<Folder> findByIdWithFetchJoin(Long folderId);


}
