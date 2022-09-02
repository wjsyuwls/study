package board.board.paging;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PageDTO {

    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private Boolean prev, next;
    private int total;
    private Criteria criteria;

    public PageDTO() {
    }

    public PageDTO(int pageCount, int total, Criteria criteria) {
        this.pageCount = pageCount;
        this.total = total;
        this.criteria = criteria;

        this.endPage = (int) (Math.ceil(criteria.getPageNum() * 1.0 / pageCount)) * pageCount;
        this.startPage = endPage - (pageCount - 1);

        realEnd = (int) (Math.ceil(total * 1.0 / criteria.getSize()));

        if (endPage > realEnd) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }

        prev = startPage > 1;
        next = endPage < realEnd;
    }
}
