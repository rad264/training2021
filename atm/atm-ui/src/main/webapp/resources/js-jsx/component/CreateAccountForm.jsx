function CreateAccountForm(props) {
	const onChange = (event) => {
		if (isNaN(event.target.value)){
			props.state = {
				...props.state,
				alert: "Invalid Input: Digits only"
			}
		} else {
			if (event.target.value.length > 6){
				props.state = {
					...props.state,
					alert: "Account number must be 6-digits"
				}
			} else {
				let accountNumber = event.target.value
				props.state = {
					...props.state,
					amount: accountNumber
				}
			}
		}
		props.updateState(props.state)
	}
	
	
	
	function resizeInput() {
	  	this.style.width = 0; this.style.width = (this.scrollWidth)+ 'px';
	}
	
	React.useEffect(() => {
		let input = document.querySelector('input');
		input.addEventListener('input', resizeInput);
		resizeInput.call(input); 
	}, [props.state])
	
	
    return (
        <div className="form">
            <h3 className="header">{props.state.message}</h3>
			<h2 className="subHeader">Enter a new 6-digit account number</h2>
            <label className="formLabel" for="accountNumber">Account Number:</label>
            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="accountNumber" onChange={onChange} value={props.state.amount} /><span className="blink"></span>
        </div>
    );
}