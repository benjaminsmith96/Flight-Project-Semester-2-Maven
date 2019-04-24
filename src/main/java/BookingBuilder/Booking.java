/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookingBuilder;

import Flights.Airline;
import Users.Customer;
import java.util.Date;

/**
 *
 * @author Cailean
 */
public class Booking {
    
    private int bookingID;
    private Customer customer;
    private Airline flight;
    private int baggage;
    private String classType;
    private float price;
    private Date bookingDate;
    
        public Booking(){
        
        }
        
        public int getBookingID() {
		return bookingID;
        }

	public Customer getCustomer() {
		return customer;
	}

	public Airline getFlight() {
		return flight;
	}

	public String getClassType() {
		return classType;
	}

	public int getBaggage() {
		return baggage;
	}

	public float getPrice() {
		return price;
	}
        
        public void setPrice(float price) {
            this.price = price;
        }

        public Date getBookingDate() {
		return bookingDate;
	}
        
        private Booking(BBuilder builder) {

		this.bookingID = builder.bookingID;
		this.customer = builder.customer;
		this.flight = builder.flight;
		this.baggage = builder.baggage;
		this.classType = builder.classType;
		this.price = builder.price;
                this.bookingDate = builder.bookingDate;
                

	}
        

        public static class BBuilder{
            
            private int bookingID;
            private Customer customer;
            private Airline flight;
            private int baggage;
            private String classType;
            private float price;
            private Date bookingDate;
            
            public BBuilder(int bookingID, Customer customer, Airline flight, int baggage, String classType, Date bookingDate){
                
                this.bookingID = bookingID;
		this.customer = customer;
		this.flight = flight;
		this.baggage = baggage;
		this.classType = classType;
		this.price = price;
                this.bookingDate = bookingDate;
                calculatePrice();
            
            }
            
            public Booking build(){
                return new Booking(this);
            }
            
            public void calculatePrice(){

                int quantity = baggage;
                int baggagePrice = (int) flight.getFlight().getBaggagePrice();
                float classPrice = 0;
            
                float totalBaggagePrice =+ quantity*baggagePrice;
            
             if(classType.matches("First Class")){
                    classPrice = flight.getFlight().getFirstPrice();
             }else if(classType.matches("Business Class")){
                 classPrice = flight.getFlight().getBusinessPrice();
             }else if(classType.matches("Economy Class")){
                 classPrice = flight.getFlight().getEconomicPrice();
             }
            
             totalBaggagePrice =+ classPrice;
            
             this.price = totalBaggagePrice;
            
            
                }
        

         }
        
          
    
}


