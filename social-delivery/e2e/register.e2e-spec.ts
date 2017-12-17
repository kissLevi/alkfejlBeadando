import { browser, by, element } from 'protractor';
import { getPath } from './getpath';

describe('Login functionality', () => {

    beforeEach(() => {
        browser.get('');
    });

    it('should fail to register a new user for empty credentials and write error messages', () => {
        //regisztraciora valtot nem tudom megnevezni
        element(by.buttonText('Regisztráció')).click();
        expect(element(by.id('mat-error-0')).isDisplayed()).toBe(true);
        expect(element(by.id('mat-error-1')).isDisplayed()).toBe(true);
        expect(getPath()).toEqual('');
    });


  /*  it('should fail to register a new user for writing an existing username', () => {
        element(by.css('input[type="text"]')).sendKeys('asdopiasidh');
        element(by.css('input[type="password"]')).sendKeys('adsasdasd');
        element(by.buttonText('Bejelentkezés')).click();
        expect(getPath()).toEqual('');
    });*/

});