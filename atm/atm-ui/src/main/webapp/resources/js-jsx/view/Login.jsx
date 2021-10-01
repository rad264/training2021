function Login(props) {
    let error;
    if (props.statusCode && props.statusCode !== 200)
        error = props.statusCode === 404 ? "User ID Not Found" : "Unexpected Error";
    return (
		<div> 
			<p>
				<span style={{color : error ? "red" : "green"}}>
				{error || <ul>
				{props.accounts.map((account, index) => (
					<li key={index}>{account}</li>
				))}
				</ul>}
				</span>
			</p>
		</div>
	)
}
