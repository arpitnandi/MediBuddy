Feature: Revamped AHC booking from MediBuddy Corporate Portal

Scenario Outline: Test the revamped AHC Booking flow for different corporates by booking corporate sponsored packages from 'portal.medibuddi.in'
    Given User opens Browser and navigates to MediBuddy Portal
    | Firefox | https://portal.medibuddy.in/ |
    
    Then User login with <Username> and <Password> in Portal
    And Redirecting to revaped AHC flow for <Corporate>
    And Select <City> in Corporate portal
    And Click on View Package for <AHC Name>
    And Click on Book Appointment for the selected <AHC Name>
    And Chose Appointment for <Provider> on <N> days from today at <Time>
    And Verify the selected Appointment details
    And Add required members
        | 1 |
        | 2 |
        | 3 |
        | 4 |
        | 5 |
    And Verify Contact number is <MOB> then Click on Confirm Booking
    And Verify the booked AHC details are same as given
    And User Sign out and proceed to Portal login page

    Examples:
      | Corporate | Username          | Password   | City  | AHC Name                                                     | Provider          | N  | Time     | Name        | Gender | MOB        |
      | CB&I      | CB01@CBI          | 03-12-1989 | Delhi | Health Check Package by CB&I India Private Limited           | The Apollo Clinic | 5  | 09:30 AM | CB self     | Male   | 8888888888 |
      | L&T       | test2@l&t         | 12-09-1993 | Delhi | Health Check Package by Larsen & Toubro Limited Construction | The Apollo Clinic | 9  | 07:00 AM | Test Spouse | Male   | 7509739694 |
      | Texas     | TestA01980@ti.com | Medi@123   | Delhi | Health Check Package by Texas Instruments India              | The Apollo Clinic | 11 | 08:30 AM | test self   | Male   | 7383499667 |
      | RealPage  | Test123@realpage  | 01-01-1984 | Delhi |Health Check Package by Real Page India Pvt Ltd               | The Apollo Clinic | 7  | 08:00 AM | Test Suresh | Male   | 7383499667 |
      | Analog    | Test01@analog     | 01011990   | Delhi | Health Check Package by Analog Devices India Pvt Ltd         | The Apollo Clinic | 6  | 09:00 AM | Test Self   | Male   | 7383499667 |
      | NOS       | 11234@NOS         | 22-07-1991 | Delhi | Health Check Package by Analog Devices India Pvt Ltd         | The Apollo Clinic | 4  | 07:30 AM | Test Self   | Male   | 9999999999 |
      | Lummus    | test06@Lummus     | 12091993   | Delhi | Health Check Package by Lummus Technology Heat Transfer BV   | The Apollo Clinic | 8  | 09:30 AM | testself    | Mele   | 8058285883 |