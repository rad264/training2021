let initialState = {
	prevState: null,
	loginStatus: false,
	keyPad: false,
	userId: "",
	currentAccount: {
		accountNumber: "",
		balance: 0
	},
	transferAccount: "",
	amount: "",
	alert: "",
	message: "",
	accountIndex: 0,
	accounts: [],
	content: "Main",
	menu: [
		{
			id: 'a',
			inactive: true,
			name: '',			
		},
		{
			id: 'b',
			inactive: true,
			name: '',
		},
		{
			id: 'c',
			inactive: true,
			name: '',			
		},
		{
			id: 'd',
			inactive: true,
			name: '',			
		},
		{
			id: 'e',
			inactive: false,
			name: 'Login',			
		},
		{
			id: 'f',
			inactive: false,
			name: 'New User',			
		},
	],
}


function Main() {
	
	const [state, setState] = React.useState(initialState);
	
	const updateState = (newState) => {
		setState(newState)
	}
	
	return (
		<>
			<div id="displayOutside">
				<div className='buttonContainer left'>
					<CoreButton direction={"left"} state={state} name={state.menu[0].name} onClick={updateState} inactive={state.menu[0].inactive}/>
					<CoreButton direction={"left"} state={state} name={state.menu[2].name} onClick={updateState} inactive={state.menu[2].inactive}/>
					<CoreButton direction={"left"} state={state} name={state.menu[4].name} onClick={updateState} inactive={state.menu[4].inactive}/>
				</div>
				<Display state={state} updateState={updateState}/>
				<div className='buttonContainer right'>
					<CoreButton direction={"right"} state={state} name={state.menu[1].name} onClick={updateState} inactive={state.menu[1].inactive}/>
					<CoreButton direction={"right"} state={state} name={state.menu[3].name} onClick={updateState} inactive={state.menu[3].inactive}/>
					<CoreButton direction={"right"} state={state} name={state.menu[5].name} onClick={updateState} inactive={state.menu[5].inactive}/>
				</div>
			</div>
			<div id='number-container'>
				<NumberPad state={state} updateState={updateState}/>
			</div>
		</>
	);
	}

ReactDOM.render(
	<>
		<Main />
		<input type="hidden"/>
	</>
	, document.getElementById("root")
);