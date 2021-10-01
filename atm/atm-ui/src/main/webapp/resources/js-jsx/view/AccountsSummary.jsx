function AccountsSummary(props) {
	let error;
	let resultText;
	props.accountsSummary.length === 0 ? resultText = "" : resultText = "Summary of Accounts: "
	if (props.statusCode && props.statusCode !== 200) {
		error = props.statusCode === 404 ? "User ID Not Found" : "Unexpected Error";
	}
	
	return (
		<div>
			<p>{resultText}</p>  
			<p>
				<span style={{color : error ? "red" : "green"}}>
				{error || <ul>
				{props.accountsSummary.map((account, index) => (
					<li key={index}>{account}</li>
				))}
				</ul>}
				</span>
			</p>
		</div>
	);
}
