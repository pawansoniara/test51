import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AlertService, EventManager } from 'ng-jhipster';

import { Product } from './product.model';
import { ProductPopupService } from './product-popup.service';
import { ProductService } from './product.service';

@Component({
    selector: 'jhi-product-delete-dialog',
    templateUrl: './product-delete-dialog.component.html'
})
export class ProductDeleteDialogComponent {

    product: Product;

    constructor(
        private productService: ProductService,
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private eventManager: EventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.productService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'productListModification',
                content: 'Deleted an product'
            });
            this.activeModal.dismiss(true);
        });
        this.alertService.success(`A Product is deleted with identifier ${id}`, null, null);
    }
}

@Component({
    selector: 'jhi-product-delete-popup',
    template: ''
})
export class ProductDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private productPopupService: ProductPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.productPopupService
                .open(ProductDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
