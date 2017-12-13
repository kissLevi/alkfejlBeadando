export enum Status{
    PENDING = "PENDING",
    ACCEPTED = "ACCEPTED",
    DONE = "DONE",
    UNRATED = "UNRATED"


}

export class Ad {
    private _id:number;
    private _status:Status;
    private _costumer_id:number;
    private _deliver_id:number;

    public constructor(
        private _name:string,
        private _description:string,
        private _location:string,
        private _price:number,
        private _deadline:number,
    ) {}
    get deliver_id():number{
        return this._deliver_id;
    }

    get costumer_id():number{
        return this._costumer_id;
    }

    get id():number{
        return this._id;
    }

    get name():string{
        return this._name;
    }
    
    get description(): string {
        return this._description;
    }
    set description(value: string) {
        this._description = value;
    }
    get location():string{
        return this._location;
    }
    set location(value:string){
        this._location = value;
    }
    get price():number{
        return this._price;
    }
    set price(value:number){
        this._price = value;
    }
    get deadline():number{
        return this._deadline;
    }
    set deadline(value:number){
        this._deadline = value;
    }
    get status():Status{
        return this._status;
    }
    set status(value:Status){
        this._status = value;
    }
}
