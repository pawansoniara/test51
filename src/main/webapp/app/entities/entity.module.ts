import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Test51VendorModule } from './vendor/vendor.module';
import { Test51ProductModule } from './product/product.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        Test51VendorModule,
        Test51ProductModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Test51EntityModule {}
