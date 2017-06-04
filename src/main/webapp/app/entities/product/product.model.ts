export class Product {
    constructor(
        public id?: string,
        public productName?: string,
        public saltName?: string,
        public description?: string,
        public packagingType?: string,
        public unitPrice?: number,
        public quntity?: number,
        public category?: string,
        public composition?: string,
        public formulation?: string,
        public imageId?: string,
        public ratings?: number,
        public eta?: any,
        public timeRequired?: string,
        public isPackagingProvided?: boolean,
        public minOrderQuantity?: number,
        public unit?: string,
        public vendorId?: string,
        public subCategory?: string,
    ) {
        this.isPackagingProvided = false;
    }
}
