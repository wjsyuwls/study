package board.board.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private int boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private LocalDateTime createdDatetime;
    private String updaterId;
    private LocalDateTime updatedDatetime;
}
