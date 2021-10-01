function NewAccountModel(userId, amount) {
    this.userId = userId;
	this.amount = amount;
    this.responseStatus = null;
    this.balance = "";
	this.message;
	this.newAccountNumber;
}