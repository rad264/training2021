class AccountsSummaryController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new ViewAccountsSummaryModel();
        this.onChange = this.onChange.bind(this);
        this.getAccountsSummary = this.getAccountsSummary.bind(this);
    }

    onChange(event) {
        this.setState(new ViewAccountsSummaryModel(event.target.value));
    }
    getAccountsSummary() {
		const userId = this.props.getUser();
        let handleResponse = (status, summary) => this.setState({responseStatus: status, accountsSummary: summary});
        handleResponse = handleResponse.bind(this);
        $.ajax({
            url: "/atm-api/users/" + userId + "/accounts/account-summary",
            type: "GET",
            success: function(response) {
                handleResponse(200, response);
            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status);
            }
        });
    }
    render() {
        return (
            <div>
                <AccountsSummaryForm onClick={this.getAccountsSummary} />
                <AccountsSummary statusCode={this.state.responseStatus} accountsSummary={this.state.accountsSummary} />
            </div>
        );
    }
}