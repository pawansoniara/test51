import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Test51SharedModule } from '../../shared';
import {
    VendorService,
    VendorPopupService,
    VendorComponent,
    VendorDetailComponent,
    VendorDialogComponent,
    VendorPopupComponent,
    VendorDeletePopupComponent,
    VendorDeleteDialogComponent,
    vendorRoute,
    vendorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...vendorRoute,
    ...vendorPopupRoute,
];

@NgModule({
    imports: [
        Test51SharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        VendorComponent,
        VendorDetailComponent,
        VendorDialogComponent,
        VendorDeleteDialogComponent,
        VendorPopupComponent,
        VendorDeletePopupComponent,
    ],
    entryComponents: [
        VendorComponent,
        VendorDialogComponent,
        VendorPopupComponent,
        VendorDeleteDialogComponent,
        VendorDeletePopupComponent,
    ],
    providers: [
        VendorService,
        VendorPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Test51VendorModule {}
