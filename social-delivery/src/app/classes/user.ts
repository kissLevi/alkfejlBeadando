export class User {
    public id:number;

    public constructor(
        private username:string,
        private password:string
    ) {}

    public getId():number{
        return this.id;
    }
    public getName():string{
        return this.username;
    }
    public getPassword():string{
        return this.password;
    }
}
