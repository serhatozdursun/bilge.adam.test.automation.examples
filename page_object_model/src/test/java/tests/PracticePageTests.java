package tests;

import pages.practicePages.PracticePage;
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
                .selectOrange()
                .isOrangeSelected()
                .selectPeach()
                .isPeachSelected()
                .deselectApple()
                .isAppleDeselected();
    }

    @Test
    public void pageNavigation() {
        new PracticePage(driver)
                .clickAllCourses()
                .selectSWTesting()
                .isSWTestingSelected()
                .waitInvisibleOfAutomationBundle()
                .isSWTestingFiltered();
    }

    @Test
    public void switchExamples() {
        new PracticePage(driver)
                .clickNewTab()
                .selectSWTesting()
                .isSWTestingSelected()
                .waitInvisibleOfAutomationBundle()
                .isSWTestingFiltered()
                .switchToPracticePage()
                .selectApple()
                .isAppleSelected()
                .selectOrange()
                .isOrangeSelected()
                .selectPeach()
                .isPeachSelected()
                .deselectApple()
                .isAppleDeselected();
    }

}
