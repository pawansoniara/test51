import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { Test51TestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ProductDetailComponent } from '../../../../../../main/webapp/app/entities/product/product-detail.component';
import { ProductService } from '../../../../../../main/webapp/app/entities/product/product.service';
import { Product } from '../../../../../../main/webapp/app/entities/product/product.model';

describe('Component Tests', () => {

    describe('Product Management Detail Component', () => {
        let comp: ProductDetailComponent;
        let fixture: ComponentFixture<ProductDetailComponent>;
        let service: ProductService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Test51TestModule],
                declarations: [ProductDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ProductService,
                    EventManager
                ]
            }).overrideTemplate(ProductDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ProductDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Product('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.product).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
