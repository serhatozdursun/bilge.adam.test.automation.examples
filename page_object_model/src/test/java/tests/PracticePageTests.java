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
                .checkAllRadioButton()
                .selectBenz()
                .isBmwSelected();
    }
}
