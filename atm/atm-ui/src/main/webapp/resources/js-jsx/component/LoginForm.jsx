function LoginForm(props) {
	const onChange = (event) => {
		let userId = event.target.value
		props.state = {
			...props.state,
			userId: userId,
		}
		props.updateState(props.state)
	}
	
	function resizeInput() {
	  	this.style.width = 0; this.style.width = this.scrollWidth + 'px';
	}
	
	React.useEffect(() => {
		let input = document.querySelector('input');
		input.addEventListener('input', resizeInput);
		resizeInput.call(input); 
	}, [props.state])
	
	
    return (
        <div className="form">
            <h3 className="header">Login</h3>
            <label className="formLabel" for="userId">User ID:</label>
            <input className="adjInput" autoFocus spellcheck="false" autocomplete="off" type="text" name="userId" onChange={onChange} value={props.state.userId} /><span className="blink"></span>
        </div>
    );
}