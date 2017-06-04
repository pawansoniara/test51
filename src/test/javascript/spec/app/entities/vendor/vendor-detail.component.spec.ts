import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils, EventManager } from 'ng-jhipster';
import { Test51TestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { VendorDetailComponent } from '../../../../../../main/webapp/app/entities/vendor/vendor-detail.component';
import { VendorService } from '../../../../../../main/webapp/app/entities/vendor/vendor.service';
import { Vendor } from '../../../../../../main/webapp/app/entities/vendor/vendor.model';

describe('Component Tests', () => {

    describe('Vendor Management Detail Component', () => {
        let comp: VendorDetailComponent;
        let fixture: ComponentFixture<VendorDetailComponent>;
        let service: VendorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Test51TestModule],
                declarations: [VendorDetailComponent],
                providers: [
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    VendorService,
                    EventManager
                ]
            }).overrideTemplate(VendorDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VendorDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VendorService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Vendor('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.vendor).toEqual(jasmine.objectContaining({id:'aaa'}));
            });
        });
    });

});
