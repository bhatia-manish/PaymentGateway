import React, {Component} from "react";
import FormControl from "@material-ui/core/FormControl";
// import withContext from "../../withContext";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import config from "../../paysafe.json";
// import { validateInput } from "./Validation";
import Helper from "./Helper";

class Checkout extends Component {
    constructor(props) {
        super(props);
        this.state = {
            customerInfo: {
                firstName: "",
                lastName: "",
                email: "",
                phone: "",
                day: "",
                month: "",
                year: "",
            },
            billingAddress: {
                street: "",
                street2: "",
                city: "",
                zip: "",
                country: "",
                state: "",
            },
            paysafeCustomerId: "1234567810",
            isPaymentProcessing: false,
            error: "",
        };

    }

    componentDidMount() {
        //including Paysafe SDK
        const script = document.createElement("script");
        script.src = config.paysafeCheckoutSDKSource;
        script.async = true;
        document.body.appendChild(script);
    }

    paysafeCheckOut = async (totalAmout) => {
        this.setState({
            isPaymentProcessing: true,
        });
        const helper = new Helper();
        //prepare input for passing to setup function beforehand
        const setupInput = await helper.prepareSetupInput(
            this.state.billingAddress,
            this.state.customerInfo,
            totalAmout,
            this.state.paysafeCustomerId
        );
        //TODO handle invalid input data before sending to setup function
        //calling paysafe checkout function
        window.paysafe.checkout.setup(
            "cHVibGljLTc3NTE6Qi1xYTItMC01ZjAzMWNiZS0wLTMwMmQwMjE1MDA4OTBlZjI2MjI5NjU2M2FjY2QxY2I0YWFiNzkwMzIzZDJmZDU3MGQzMDIxNDUxMGJjZGFjZGFhNGYwM2Y1OTQ3N2VlZjEzZjJhZjVhZDEzZTMwNDQ=",
            setupInput,
            helper.callBackFunction,
            helper.closeCallBack
        );
        try {
            //check payment status when checkout.setup function finishes execution
            const status = await helper.paymentStatus;
            if (status.status === "success") {
                console.log(status);
            }
        } catch (err) {
            this.setState({
                isPaymentProcessing: false,
            });
        }
    };

    //checkout using paysafe
    handleCheckout = async (event) => {
        event.preventDefault();
        await this.paysafeCheckOut(2000);
    };


    onCustomerDetailsInputChange = (event) => {
        const changedInput = {...this.state.customerInfo};
        changedInput[event.target.id] = event.target.value;
        this.setState({
            customerInfo: changedInput,
        });
    };

    onBillingAddressInputChange = (event) => {
        const changedInput = {...this.state.billingAddress};
        changedInput[event.target.id] = event.target.value;
        this.setState({
            billingAddress: changedInput,
        });
    };

    render() {
        return (
            <center>
                <h1>Customer Info</h1>
                <form onSubmit={(event) => this.handleCheckout(event)}>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="firstName">First Name</InputLabel>
                        <Input
                            value={this.state.customerInfo.firstName}
                            onChange={this.onCustomerDetailsInputChange}
                            id="firstName"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="lastName">Last Name</InputLabel>
                        <Input
                            value={this.state.customerInfo.lastName}
                            onChange={this.onCustomerDetailsInputChange}
                            id="lastName"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="email">Email</InputLabel>
                        <Input
                            value={this.state.customerInfo.email}
                            onChange={this.onCustomerDetailsInputChange}
                            id="email"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="phone">Phone Number</InputLabel>
                        <Input
                            value={this.state.customerInfo.phone}
                            onChange={this.onCustomerDetailsInputChange}
                            id="phone"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "8.3%"}}>
                        <InputLabel htmlFor="day">Day</InputLabel>
                        <Input
                            value={this.state.customerInfo.day}
                            onChange={this.onCustomerDetailsInputChange}
                            id="day"
                        />
                    </FormControl>
                    <FormControl style={{width: "8.3%"}}>
                        <InputLabel htmlFor="month">Month</InputLabel>
                        <Input
                            value={this.state.customerInfo.month}
                            onChange={this.onCustomerDetailsInputChange}
                            id="month"
                        />
                    </FormControl>
                    <FormControl style={{width: "8.3%"}}>
                        <InputLabel htmlFor="year">Year</InputLabel>
                        <Input
                            value={this.state.customerInfo.year}
                            onChange={this.onCustomerDetailsInputChange}
                            id="year"
                        />
                    </FormControl>
                    <br/>
                    <br/>
                    <h1>Billing Address</h1>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="street">Address Street</InputLabel>
                        <Input
                            value={this.state.billingAddress.street}
                            onChange={this.onBillingAddressInputChange}
                            id="street"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="street2">Address Street 2</InputLabel>
                        <Input
                            value={this.state.billingAddress.street2}
                            onChange={this.onBillingAddressInputChange}
                            id="street2"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="city">City</InputLabel>
                        <Input
                            value={this.state.billingAddress.city}
                            onChange={this.onBillingAddressInputChange}
                            id="city"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="zip">Zip Code</InputLabel>
                        <Input
                            value={this.state.billingAddress.zip}
                            onChange={this.onBillingAddressInputChange}
                            id="zip"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="state">State Code</InputLabel>
                        <Input
                            value={this.state.billingAddress.state}
                            onChange={this.onBillingAddressInputChange}
                            id="state"
                        />
                    </FormControl>
                    <br/>
                    <FormControl style={{width: "25%"}}>
                        <InputLabel htmlFor="countryzip">Country Code</InputLabel>
                        <Input
                            value={this.state.billingAddress.country}
                            onChange={this.onBillingAddressInputChange}
                            id="country"
                        />
                    </FormControl>
                    <br/>
                    {this.state.error && (
                        <div className="has-text-danger">{this.state.error}</div>
                    )}
                    <br/>
                    <button
                        type="submit"
                        className="button is-primary is-medium"
                        disabled={this.state.isPaymentProcessing}
                    >
                        Proceed To Payment
                    </button>
                </form>
            </center>
        );
    }
}

export default Checkout