class DepositController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new DepositModel();
		this.state = {accounts : this.props.getAccounts()};
        this.onChange = this.onChange.bind(this);
        this.deposit = this.deposit.bind(this);
    }

    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
    }

    deposit() {
        const accountNumber = this.state.accountNumber;	
		const amount = this.state.amount;
        let handleResponse = (status, message) => this.setState({responseStatus: status, message: message});
        handleResponse = handleResponse.bind(this);
        $.ajax({
            url: "/atm-api/accounts/" + accountNumber + "/deposits",
            type: "POST",
			headers: { 
        		'Accept': 'application/json',
        		'Content-Type': 'application/json' 
    		},
			data: amount,
            success: function(response) {
                handleResponse(200, "Deposit successful.");
				alert("Deposit successful");
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status, "Deposit could not be processed.");
				alert("Deposit could not be processed. Please make sure to enter a positive amount.");
            }
        });
    }
    render() {
        return (
            <div>
                <DepositForm accountNumber={this.state.accountNumber} accounts={this.state.accounts} amount={this.state.amount} onChange={this.onChange} onClick={this.deposit} />
                <Deposit statusCode={this.state.responseStatus} message={this.state.message} />
            </div>
        );
    }
}