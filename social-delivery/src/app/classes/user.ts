export enum Role{
    ADMIN ="ADMIN",
    USER ="USER",
    GUEST="GUEST"
}

export class User {
    public _id:number;

    public constructor(
        private _username:string,
        private _password:string,
        private _balance:number,
        private _role:Role
    ) {}

    public get id():number{
        return this._id;
    }
    public get username():string{
        return this._username;
    }
    public get password():string{
        return this._password;
    }
    public get role():Role{
        return this._role;
    }
    public get balance():number{
        return this._balance;
    }
}
