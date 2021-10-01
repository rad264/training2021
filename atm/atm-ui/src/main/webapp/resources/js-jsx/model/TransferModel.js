function TransferModel(accountNumber, accountNumberToTransfer, amount) {
    this.accountNumber = accountNumber;
	this.accountNumberToTransfer = accountNumberToTransfer; 
    this.responseStatus = null;
    this.amount = amount;
	this.message;
}