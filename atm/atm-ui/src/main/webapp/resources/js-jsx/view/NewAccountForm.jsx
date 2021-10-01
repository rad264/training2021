function NewAccountForm(props) {
    return (
        <div>
            <h3>Open a New Account</h3>
			<label for="amount">Amount (USD):</label>
			<input type="text" name="amount" onChange={props.onChange} value={props.amount} placeholder="Enter Opening Amount" />
            <button onClick={props.onClick} className="button">Open Account</button>
        </div>
    );
}