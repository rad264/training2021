class TransferController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new TransferModel();
		this.state = {accounts : this.props.getAccounts()};
        this.onChange = this.onChange.bind(this);
        this.transfer = this.transfer.bind(this);
    }

    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
    }

    transfer() {
        const accountNumber = this.state.accountNumber;
		const accountNumberToTransfer = this.state.accountNumberToTransfer;	
		const amount = this.state.amount;
        let handleResponse = (status, message) => this.setState({responseStatus: status, message: message});
        handleResponse = handleResponse.bind(this);
        $.ajax({
            url: "/atm-api/accounts/" + accountNumber + "/transfers?accountNumberToTransfer=" + accountNumberToTransfer,
            type: "POST",
			headers: { 
        		'Accept': 'application/json',
        		'Content-Type': 'application/json' 
    		},
			data: amount,
            success: function(response) {
                handleResponse(200, "Transfer successful.");
				alert("Transfer successful.");
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status, "Transfer could not be processed.");
				alert("Transfer could not be processed. Please make sure to enter a positive amount that does not exceed the sending account's current balance.");
            }
        });
    }
    render() {
        return (
            <div>
                <TransferForm accountNumber={this.state.accountNumber} accountNumberToTransfer={this.state.accountNumberToTransfer} accounts={this.state.accounts} amount={this.state.amount} onChange={this.onChange} onClick={this.transfer} />
                <Transfer statusCode={this.state.responseStatus} message={this.state.message} />
            </div>
        );
    }
}