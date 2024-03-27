package com.example.web.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Board(
    var title: String,
    var author: String,
    var password: String,
    var content: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    @CreatedDate
    var createdAt: LocalDateTime? = null
        protected set

    fun update(title: String?, author: String?, content: String?) {
        if (title != null) this.title = title;
        if (author != null) this.author = author;
        if (content != null) this.content = content;
    }
}