package tests;

import Pages.PracticePage;
import base.BasePage;
import org.junit.jupiter.api.Test;

public class PracticePageTests extends BasePage {

    @Test
    public void radioButton() {
        new PracticePage(driver)
                .clickBmwRadio()
                .assertBmwButtonIsSelected()
                .clickHondaRadio()
                .assertHondaButtonIsSelected()
                .clickBenzRadio()
                .assertBenzButtonIsSelected()
                .checkAllRadioButton();
    }

    @Test
    public void selectExample() {
        new PracticePage(driver)
                .selectBenz()
                .isBenzSelected();
    }

    @Test
    public void multipleSelect() {

        new PracticePage(driver)
                .selectApple()
                .isAppleSelected()
                .deselectApple()
                .isAppleDeselected();

    }

}
