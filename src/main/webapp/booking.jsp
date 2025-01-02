<!DOCTYPE html>
<html>
<head>
    <title>Booking Page</title>
</head>
<body>
    <h1>Welcome, ${cab.fullName}!</h1>
    <p>Proceed to book a cab.</p>
    <form action="/book" method="post">
        <input type="hidden" name="email" value="${cab.email}" />
        <input type="hidden" name="fullName" value="${cab.fullName}" />
        <input type="hidden" name="pwd" value="${cab.password}" />
        <input type="hidden" name="phoneNumber" value="${cab.phoneNumber}" />
        <label>Location:</label><input type="text" name="location" required /><br />
        <label>Destination:</label><input type="text" name="destination" required /><br />
        <label>Car Type:</label><input type="text" name="carType" required /><br />
        <label>Distance (km):</label><input type="number" name="distance" required /><br />
        <button type="submit">Book Now</button>
    </form>
</body>
</html>