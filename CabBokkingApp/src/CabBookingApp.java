import java.util.Scanner;


public class CabBookingApp {
	
	
	public enum CabType {
		MICRO, SEDAN, SUV; 
	}
	
	Scanner sc = new Scanner(System.in);
	
	public static CabList cabListObj = new CabList();
	public static CustomerList customerListObj = new CustomerList();
	public static BookingList bookingListObj = new BookingList();
	public static ZohoCabs zohoCabsObj = new ZohoCabs();
	
	
	CabTypeDetails selectCabType() {
		
		CabType cabType = null;
		double baseFare = 0, farePerKM = 0;
		int numberOfSeats = 0;
		int cabTypeOption;
		System.out.println("\n1) MICRO 2) SEDAN 3) SUV 4) Exit");
		cabTypeOption = sc.nextInt();

		switch(cabTypeOption) {
		
		case 1:
			cabType = CabType.MICRO;
			baseFare = 100;
			farePerKM = 20;
			numberOfSeats = 4;
			break;
		
		case 2:
			cabType = CabType.SEDAN;
			baseFare = 150;
			farePerKM = 30;
			numberOfSeats = 4;
			break;
		
		case 3:
			cabType = CabType.SUV;
			baseFare = 200;
			farePerKM = 40;
			numberOfSeats = 6;
			break;
		
		case 4:
			System.out.println("Driver not registered!! Please Register again");
			return null;
			
		default:
			System.out.println("Enter a valid option");
			selectCabType();
			return null;
		}
		CabTypeDetails cabTypeObj = new CabTypeDetails(cabType, baseFare, farePerKM, numberOfSeats);
		return cabTypeObj;
	}
	
	void showRegistrationMenu() {
		char registrationOption = '\0';

		System.out.println("=========================================================================");
		System.out.println("\nPlease Select an option\n");
		System.out.println("1) Driver Registration 2) Customer Registration 3) Exit\n");
		System.out.println("=========================================================================");
		
		registrationOption = sc.next().charAt(0);
		
		switch(registrationOption) {
		
		
		case '1':
			String driverName, cabNumberPlate, driverPhoneNumber, driverPassword;
			
			System.out.println("Enter the Driver Name:");
			sc.nextLine();
			driverName = sc.nextLine();
			System.out.println("Enter the Cab's Number Plate:");
			cabNumberPlate = sc.nextLine();
			System.out.println("Enter the Driver's Phone Number:");
			driverPhoneNumber = sc.nextLine();
			
			//As Phone Number is the key we make a check to see if phone number already exists if not else we add the Driver
			if(cabListObj.isDriverExists(driverPhoneNumber)) {
				System.out.println("Phone Number is Already Registered, Please enter a different number");
				break;
			}
			
			System.out.println("Select the Type of cab you own:");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			CabTypeDetails cabTypeObj = selectCabType();
			if(cabTypeObj == null) {
				break;
			}
			
			System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			System.out.println("Enter your password:");
			sc.nextLine();
			driverPassword = sc.nextLine();
			
			
			Account newAccount = new Account();
			Cab newCab = new Cab(driverName, cabNumberPlate, driverPhoneNumber, cabTypeObj, driverPassword, newAccount);
			
			
			//Here we add the Driver
			cabListObj.addCab(driverPhoneNumber, newCab);
			System.out.println("Successfully Registered Driver");
			System.out.println("=========================================================================");
			System.out.println(newCab.toString());
			
			break;
		
		case '2':
			String customerName, customerPhoneNumber, customerPassword;
			System.out.println("Enter your Name:");
			sc.nextLine();
			customerName = sc.nextLine();
			System.out.println("Enter your Phone Number:");
			customerPhoneNumber = sc.nextLine();
			
			//As Phone Number is the key we make a check to see if phone number already exists if not else we add the customer
			if(customerListObj.isCustomerExists(customerPhoneNumber)) {
				System.out.println("Phone Number is Already Registered, Please enter a different number");
				break;
			}
			
			System.out.println("Enter your Password:");
			customerPassword = sc.nextLine();
			
			Account olaWallet = new Account();
			Customer newCustomer = new Customer(customerName, customerPhoneNumber, customerPassword, olaWallet);
			
			
			//Here we add the customer
			customerListObj.addCustomer(customerPhoneNumber, newCustomer);
			System.out.println("Successfully Registered Customer");
			System.out.println("=========================================================================");
			System.out.println(newCustomer.toString());
			
			break;
		
		case '3':
			System.out.println("Going to main Menu");
			break;
		
		default:
			System.out.println("Enter a valid option");
			break;
		
		}
	}
	
	
	void showDriverMenu(String phoneNumber) {
		char driverOption = '\0';
		
		Cab cab = cabListObj.getCab(phoneNumber);
		
		System.out.println("\nPlease Select an option\n");
		System.out.println("1) Take Rides 2) Show Earnings In Zoho Wallet 3) Logout\n");
		System.out.println("=========================================================================");
		
		driverOption = sc.next().charAt(0);
		
		switch(driverOption) {
		
		case '1':
			String area;
			double xCoordinate, yCoordinate;
			
			System.out.println("Enter your Current Area:");
			sc.nextLine();
			area = sc.nextLine();
			System.out.println("Enter your Current Location (Longitude and Latitude):");
			xCoordinate = sc.nextDouble();
			yCoordinate = sc.nextDouble();
			
			Location newLocation = new Location(area, xCoordinate, yCoordinate);
			cabListObj.updateCabLocation(cab, newLocation);
			cabListObj.updateCabStatus(cab, true);
			System.out.println("You are ready to take Rides");
			//System.out.println(cabListObj.driverList.get(phoneNumber).toString());
			break;
		
		case '2':
			System.out.println("Your Earning in Zoho Wallet is Rs:" + cab.getAccount().getAccountBalance());
			break;
		
		case '3':
			cabListObj.updateCabStatus(cab, false);
			System.out.println("Successfully Logged Out");
			break;
			
		default:
			System.out.println("Enter a valid option");
			break;
		}
		
	}
	
	void showCustomerMenu(String phoneNumber) {
		char customerOption = '\0';
		
		//Customer customer = customerListObj.getCustomer(phoneNumber);
		
		System.out.println("\nPlease Select an option\n");
		System.out.println("1) Book A Cab 2) Show Booking History 3) Add Money to Wallet 4) Check Zoho Wallet Balance 5) Logout\n");
		System.out.println("=========================================================================");
		
		Customer customer = customerListObj.getCustomer(phoneNumber);
		
		customerOption = sc.next().charAt(0);
		
		switch(customerOption) {
		
		case '1':
			String pickUpArea, dropArea, customerId = phoneNumber, driverId;
			double pickUpXCoordinate, pickUpYCoordinate, dropXCoordinate, dropYCoordinate;
			CabType cabType = null;
			
			System.out.println("Enter your Pick Up Area:");
			sc.nextLine();
			pickUpArea = sc.nextLine();
			System.out.println("Enter your Pick Up location (Longitude and Latitude):");
			pickUpXCoordinate = sc.nextDouble();
			pickUpYCoordinate = sc.nextDouble();

			Location pickUpLocation = new Location(pickUpArea, pickUpXCoordinate, pickUpYCoordinate);
			
			System.out.println("Enter your Drop Area:");
			sc.nextLine();
			dropArea = sc.nextLine();
			System.out.println("Enter your Drop location (Longitude and Latitude):");
			dropXCoordinate = sc.nextDouble();
			dropYCoordinate = sc.nextDouble();
			
			Location dropLocation = new Location(dropArea, dropXCoordinate, dropYCoordinate);
			
			System.out.println("Enter the type of Cab you want to book:");
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			int cabTypeOption;
			System.out.println("\n1) MICRO 2) SEDAN 3) SUV");
			cabTypeOption = sc.nextInt();

			switch(cabTypeOption) {
			
			case 1:
				cabType = CabType.MICRO;
				break;
			
			case 2:
				cabType = CabType.SEDAN;
				break;
			
			case 3:
				cabType = CabType.SUV;
				break;
				
			default:
				System.out.println("Enter a valid option");
				break;
			}
			System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			if(cabType == null) {
				System.out.println("Please Book Again");
				break;
			}
			
			driverId = cabListObj.findNearestCab(cabType, pickUpLocation);
			
			if(driverId == null) {
				System.out.println("Sorry No cabs Available, Try Booking after sometime");
				break;
			}
			
			
			//HERE WE MAKE THE BOOKING
			
			Booking newBooking = new Booking(customerId, driverId, cabType, pickUpLocation, dropLocation);
			
			//HERE WE UPDATE THE ACCOUNTS OF DRIVER, CUSTOMER AND ZOHO CABS
			
			if(newBooking.getPaymentMode() == Payment.PaymentMode.WALLET_MONEY) {
				
				Payment newPayment = new Payment();
				newPayment.payThroughZohoWallet(customerId, driverId, newBooking.getTripFare());
			}
			
			else if(newBooking.getPaymentMode() == Payment.PaymentMode.CASH) {
				
				Payment newPayment = new Payment();
				newPayment.payThroughCash(customerId, driverId, newBooking.getTripFare());
			}
			
			else if(newBooking.getPaymentMode() == Payment.PaymentMode.WALLET_MONEY_AND_CASH) {
				
				Payment newPayment = new Payment();
				double zohoWalletMoney = customerListObj.getCustomer(customerId).getAccount().getAccountBalance();
				newPayment.payThroughZohoWallet(customerId, driverId, zohoWalletMoney);
				newPayment.payThroughCash(customerId, driverId, newBooking.getTripFare() - zohoWalletMoney);
			}
			
			
			//HERE WE UPDATE THE CABS NEW LOCATION
			Cab cab = cabListObj.getCab(driverId);
			cabListObj.updateCabLocation(cab, dropLocation);
			
			//HERE WE ADD THE BOOKING TO THE LIST
			bookingListObj.addBooking(newBooking);
			System.out.println("Successfully booked cab, Booking detalis are given below:");
			System.out.println("=========================================================================");
			System.out.println(newBooking.toString());
			break;
		
		case '2':
			System.out.println("Under implementation");
			break;
		
		case '3':
			double moneyToDeposit;
			System.out.println("How much money do you want to deposit");
			moneyToDeposit = sc.nextDouble();
			customerListObj.depositMoneyToWallet(customer, moneyToDeposit);
			System.out.println("Successfully deposited Rs:" + moneyToDeposit + " to your Zoho Wallet");
			break;
		
		case '4':
			System.out.println("Your Zoho Account Balance is Rs:"+customer.getAccount().getAccountBalance());
			break;
		
		case '5':
			System.out.println("Successfully Logged Out");
			break;
		
		default:
			System.out.println("Enter a valid option");
			break;
		}
	}

	
	void showLogInMenu() {
		char logInOption = '\0';
		
		do {
		
			System.out.println("=========================================================================");
			System.out.println("\nPlease Select an option\n");
			System.out.println("1) Driver Log-In 2) Customer Log-In 3) Exit\n");
			System.out.println("=========================================================================");
			
			logInOption = sc.next().charAt(0);
			
			switch(logInOption) {
			
			
			case '1':
				String driverPhoneNumber, driverPassword;
				System.out.println("Enter your Phone Number:");
				sc.nextLine();
				driverPhoneNumber = sc.nextLine();
				if(!cabListObj.isDriverExists(driverPhoneNumber)) {
					System.out.println("Phone Number is Not Registered, Please check the number");
					break;
				}
				System.out.println("Enter your Password:");
				driverPassword = sc.nextLine();
				if(!cabListObj.isPasswordMatch(driverPhoneNumber, driverPassword)) {
					System.out.println("Password does not Match");
					break;
				}
				System.out.println("Successfully Logged in as Driver");
				System.out.println("=========================================================================");
				showDriverMenu(driverPhoneNumber);
				
				break;
			
			case '2':
				String customerPhoneNumber, customerPassword;
				System.out.println("Enter your Phone Number:");
				sc.nextLine();
				customerPhoneNumber = sc.nextLine();
				if(!customerListObj.isCustomerExists(customerPhoneNumber)) {
					System.out.println("Phone Number is Not Registered, Please check the number");
					break;
				}
				System.out.println("Enter your Password:");
				customerPassword = sc.nextLine();
				if(!customerListObj.isPasswordMatch(customerPhoneNumber, customerPassword)) {
					System.out.println("Password does not Match");
					break;
				}
				System.out.println("Successfully Logged in as Customer");
				System.out.println("=========================================================================");
				showCustomerMenu(customerPhoneNumber);
				
				break;
			
			case '3':
				System.out.println("Going to main Menu");
				break;
			
			default:
				System.out.println("Enter a valid option");
				break;
			}
	 }while(logInOption != '3');
		
	}
	
	void showAdminMenu() {
		String password;
		System.out.println("Enter The admin Password:");
		sc.nextLine();
		password = sc.nextLine();
		if(!zohoCabsObj.isPasswordMatch(password)) {
			System.out.println("Password does not Match");
			return;
		}
		char adminOption = '\0';
		System.out.println("=========================================================================");
		System.out.println("\nPlease Select an option\n");
		System.out.println("1) Check Account Balance 2) Exit\n");
		System.out.println("=========================================================================");
		
		adminOption = sc.next().charAt(0);
		
		switch(adminOption) {
		
		
		case '1': 
			System.out.println(zohoCabsObj.getAccount().getAccountBalance());
			break;
		
		case '2':
			System.out.println("Successfully Logged Out");
			break;
		
		default:
			System.out.println("Enter a valid option");
			break;
			
		}
	}
	
	
	void showMenu() {
		char option = '\0';
		System.out.println("<<<<<<<<<< WELCOME TO ZOHO CABS >>>>>>>>>>");

		do {
			
			System.out.println("=========================================================================");
			System.out.println("\nPlease Select an option\n");
			System.out.println("1) Register 2) Log In 3) Log in As Admin 4) Exit\n");
			System.out.println("=========================================================================");
			option = sc.next().charAt(0);
			
			switch(option) {
			
			case '1': 
				showRegistrationMenu();
				break;
			case '2':
				showLogInMenu();
				break;
			case '3':
				showAdminMenu();
				break;
			case '4':
				System.out.println("<<<<<<<<<<<< EXITING ZOHO CABS >>>>>>>>>>>>");
				break;
			default:
				System.out.println("Enter a valid option");
				break;
			}
			
			
		}while(option != '4');
	}
	
	
	public static void main(String[] args) {
		CabBookingApp cabBookingAppObj = new CabBookingApp();
		cabBookingAppObj.showMenu();
	}
}
