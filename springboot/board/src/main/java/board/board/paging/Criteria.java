package board.board.paging;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
public class Criteria {

    private int pageNum;
    private int size;

    public Criteria() {
        this(1, 5);
    }

    public Criteria(int pageNum, int size) {
        this.pageNum = pageNum;
        this.size = size;
    }

    public String getLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", pageNum)
                .queryParam("size", size);

        return builder.toUriString();
    }
}
