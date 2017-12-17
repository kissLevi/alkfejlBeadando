import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Login functionality', () => {

    beforeEach(() => {
        browser.get('');
    });

    it('should fail to register a new user for empty credentials and write error messages', () => {
        element.all(by.css('.mat-tab-label')).get(1).click();
        browser.sleep(1000);
        element(by.buttonText('Regisztráció')).click();
        expect(element(by.id('mat-error-0')).isDisplayed()).toBe(true);
        expect(element(by.id('mat-error-1')).isDisplayed()).toBe(true);
        expect(getPath()).toEqual('');
    });


    it('should fail to register a new user for writing an existing username', () => {
       element.all(by.css('.mat-tab-label')).get(1).click();
        browser.sleep(1000);
        element(by.css('input[type="text"]')).sendKeys('admin');
        element(by.css('input[type="password"]')).sendKeys('adsasdasd');
        element(by.buttonText('Regisztráció')).click();
        expect(element(by.id('mat-error-0')).isDisplayed()).toBe(true);
        expect(getPath()).toEqual('');
    });

    /*it('should successfully register a new user for writing good input', () => {
        element.all(by.css('.mat-tab-label')).get(1).click();
         browser.sleep(1000);
         element(by.css('input[type="text"]')).sendKeys('what');
         element(by.css('input[type="password"]')).sendKeys('asd');
         element(by.buttonText('Regisztráció')).click();
         expect(getPath()).toEqual('');
         browser.sleep(1000);
         expect(element(by.className("success")).getText).toEqual("Sikeres regisztráció");
         browser.sleep(100000);
     });*/

});