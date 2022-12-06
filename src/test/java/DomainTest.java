import domain.Category;
import domain.Menu;
import domain.MenuRepository;
import domain.TableRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DomainTest {

    @Test
    void 임시테스트() {
        Menu menu1 = new Menu(1, "썬더치킨", Category.BEVERAGE, 1000000);
        assertThat(menu1).isEqualTo(MenuRepository.getMenu(2));
    }
}
