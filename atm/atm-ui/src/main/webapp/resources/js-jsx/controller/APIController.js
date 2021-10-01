const login = async (userId) => {
	return await $.ajax({
		url: "/atm-api/users/" + userId,
		type: "GET"
	});

};

const checkBalance = async (accountNumber) => {
	return await $.ajax({
		url: "/atm-api/accounts/" + accountNumber,
		type: "GET"
	})
};

const deposit = async (accountNumber, amount) => {
	console.log("/atm-api/accounts/" + accountNumber + "/deposit/" + amount)
	return await $.ajax({
		url: "/atm-api/accounts/" + accountNumber + "/deposit/" + amount,
		type: "PUT"
	})
}

const withdraw = async (accountNumber, amount) => {
	return await $.ajax({
		url: "/atm-api/accounts/" + accountNumber + "/withdraw/" + amount,
		type: "PUT"
	})
}

const createUser = async (userId) => {
	return await $.ajax({
		url: "/atm-api/users/create/" + userId,
		type: "POST"
	})
}

const createAccount = async (userId, accountNumber) => {
	return await $.ajax({
		url: "/atm-api/users/" + userId + "/create/" + accountNumber,
		type: "PUT"
	})
}

const transfer = async (fromAccount, toAccount, amount) => {
	return await $.ajax({
		url: "/atm-api/accounts/"+ fromAccount + "/transfer/" + toAccount + "/" + amount,
		type: "PUT"
	})
}

const createTransaction = async (accountNumber, amount, type, description) => {
	return await $.ajax({
		url: "/atm-api/transactions/create/" + accountNumber + "/" + amount + "/" + type + "/" + description,
		type: "POST"
	})
}

const getTransaction = async (transactionId) => {
	return await $.ajax({
		url: "/atm-api/transactions/" + transactionId,
		type: "GET"
	})
}

const getAllTransactionsByAccount = async (accountNumber) => {
	return await $.ajax({
		url: "/atm-api/transactions/accounts/" + accountNumber,
		type: "GET"
	})
}