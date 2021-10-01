function AccountsSummaryForm(props) {
	return (
		<div>
			<h3>Summary of Accounts</h3>
			<button onClick={props.onClick} className="button">View Summary of All Accounts</button>
		</div>
	);
}
