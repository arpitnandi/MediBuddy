Feature: MediBuddy Book Health Check functionality test

Scenario Outline: Test Book Health Check flow  for 'Retail' User from 'Infinity' portal.
    Given User opens Browser and navigates to URL
    | firefox | https://www.medibuddy.in/ |
    
    Then Click on <Product> Icon from Dashboard
    And Select <City> and <Category>
    And Click on View Package button of Package <N>
    And Click on Book Now button of Package
    And Select Appointment on <Days> days from today at <Time>
    And Login with <Username> and <Password>
    And Fill Patient's <Name>, <Gender>, <DOB>, <MOB> and <Email>
    And Select <Address> for test sample pickup
    And Click on Continue Booking button for <Category> Order
    And Apply required <PromoCode>
    And Click on Confirm button and Make Payment
    And User Verify the Order in Track Orders
    And User signout from application

    Examples:
      | Username               | Password       | Product      | City    | Category   | N  | Days  | Time     | Name         | Gender | DOB        | MOB        | Email       | Address                   | PromoCode | Wallet |
      | arpit.nandi@docsapp.in | Arpit@23091992 | Health Check | Delhi   | Preventive | 7  | 6     | 07:30 AM | Hamid Sheikh | Male   | 03_03_1966 | 8989898989 | ru.Go@gm.in | Add l 1, Add l 2          | testdev01 | MA     | 
      | TestA01980@ti.com      | Medi@123       | Health Check | Kolkata | Diabetes   | 5  | 10    | 09:30 AM | Ravina Sen   | Female | 17_11_1978 | 9000000009 | rv.Sn@gm.co | 23/ PArk Street, sector B | testdev01 | MB     |