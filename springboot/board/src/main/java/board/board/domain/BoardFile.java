package board.board.domain;

import lombok.Data;

@Data
public class BoardFile {
    private int idx;
    private int boardIdx;
    private String originalFileName;
    private String storedFilePath;
    private long fileSize;
}
