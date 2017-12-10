

export enum RatingType{
    DELIVER = "DELIVER",
    CUSTOMER = "CUSTOMER"
}
export enum Status{
    PENDING = "PENDING",
    DONE = "DONE"
}

export class Rating {
    constructor(
        private _rating:number,
        private _description:string,
        private _status:Status,
        private _type:RatingType,
        private _id:number,
        private _ratedUser:string,
        private _raterUser:string
    ) {}

    public get id():number{
        return this._id;
    }
    
    public get rating():number{
        return this._rating;
    }

    public get description():string{
        return this._description;
    }

    public get type():RatingType{
        return this._type;
    }

    public get ratedUser():string{
        return this._ratedUser;
    }

    public get raterUser():string{
        return this._raterUser;
    }

    public get status():Status{
        return this._status;
    }

}
