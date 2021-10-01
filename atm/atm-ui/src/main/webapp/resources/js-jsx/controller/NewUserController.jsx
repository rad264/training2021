class NewUserController extends React.Component {
    constructor(props) {
        super(props);
        this.state = new NewUserModel();
        this.onChange = this.onChange.bind(this);
        this.createNewUser = this.createNewUser.bind(this);
    }
    onChange(event) {
        this.setState({
			[event.target.name]: event.target.value
		});
		
    }
    createNewUser() {
        const userId = this.state.userId;
        let handleResponse = (status, message) => this.setState({responseStatus: status, message: message});
        handleResponse = handleResponse.bind(this);
		let updateUser = this.props.updateUser;
		updateUser = updateUser.bind(this);
		let updateAccounts = this.props.updateAccounts;
		updateAccounts = updateAccounts.bind(this);
        $.ajax({
            url: "/atm-api/users/" + userId,
            type: "POST",
			headers: { 
        		'Accept': 'application/json',
        		'Content-Type': 'application/json' 
    		},
            success: function(response) {
                handleResponse(200, "User created");
				updateUser(userId);
				updateAccounts([]);
				alert("User successfully created.")

            },
            error: function(xhr, status, error) {
                handleResponse(xhr.status, "User not created");
				alert("User could not be created.")
            }
        });
    }
    render() {
        return (
            <div>
                <NewUserForm userId={this.state.userId} onChange={this.onChange} onClick={this.createNewUser} />
                <NewUser statusCode={this.state.responseStatus} message={this.state.message} />
            </div>
        );
    }
}