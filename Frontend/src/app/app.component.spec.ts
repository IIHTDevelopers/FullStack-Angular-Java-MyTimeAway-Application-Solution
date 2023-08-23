import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { Router } from '@angular/router';
import { By } from '@angular/platform-browser';
import { Component } from '@angular/core';

describe('AppComponent', () => {
    let fixture: ComponentFixture<AppComponent>;
    let component: AppComponent;
    let router: Router;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            declarations: [AppComponent],
            imports: [RouterTestingModule.withRoutes([
                { path: 'user', component: DummyComponent },
                { path: 'admin', component: DummyComponent },
            ]),],
        }).compileComponents();

        fixture = TestBed.createComponent(AppComponent);
        router = TestBed.inject(Router);
        component = fixture.componentInstance;
    });

    describe('boundary', () => {
        it('should create the app', () => {
            expect(component).toBeTruthy();
        });

        it(`should have as title 'MyTime Away Application'`, () => {
            expect(component.title).toEqual('MyTime Away Application');
        });

        it('should render title', () => {
            fixture.detectChanges();
            const compiled = fixture.nativeElement;
            expect(compiled.querySelector('h1').textContent).toContain('MyTime Away Application');
        });

        it('should navigate to /user when clicking on the "User" link', () => {
            const userLink = fixture.debugElement.query(By.css('a[routerLink="/user"]'));
            userLink.nativeElement.click();
            fixture.detectChanges();
            expect(router.url).toBe('/user');
        });

        it('should navigate to /admin when clicking on the "Admin" link', () => {
            const adminLink = fixture.debugElement.query(By.css('a[routerLink="/admin"]'));
            adminLink.nativeElement.click();
            fixture.detectChanges();
            expect(router.url).toBe('/admin');
        });
    });
});

// DummyComponent can be an empty component used for testing routes
@Component({ template: '' })
class DummyComponent { }