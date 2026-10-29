$base = "http://localhost:8081/api"

function Save($path, $obj) {
  $json = $obj | ConvertTo-Json -Depth 5
  Invoke-RestMethod -Method Post -Uri "$base/$path/save" -ContentType "application/json" -Body $json
}

# 1. Expertises
"Routing","Wireless","Cabling","Network Security" | ForEach-Object { Save "expertises" @{ name = $_ } }
$exp = Invoke-RestMethod "$base/expertises/all"
function ExpId($n) { ($exp | Where-Object { $_.name -eq $n }).id }

# 2. Locations
1..4 | ForEach-Object { Save "locations" @{ locationNumber = $_; name = "Office $_" } }
$loc = Invoke-RestMethod "$base/locations/all"
function LocId($n) { ($loc | Where-Object { $_.locationNumber -eq $n }).id }

# 3. Requesters
@(
  @{firstName="Alice";   lastName="Abdallah";  staffNumber="ST-1021"; email="alice@auca.ac.rw";   department="IT"},
  @{firstName="Brian";   lastName="Abdallah";  staffNumber="ST-1022"; email="brian@auca.ac.rw";   department="Finance"},
  @{firstName="Chantal"; lastName="Uwase";     staffNumber="ST-1023"; email="chantal@auca.ac.rw"; department="Library"},
  @{firstName="David";   lastName="Habimana";  staffNumber="ST-1024"; email="david@auca.ac.rw";   department="Admissions"},
  @{firstName="Esther";  lastName="Mukamana";  staffNumber="ST-1025"; email="esther@auca.ac.rw";  department="Registry"}
) | ForEach-Object { Save "requesters" $_ }
$req = Invoke-RestMethod "$base/requesters/all"
function ReqId($s) { ($req | Where-Object { $_.staffNumber -eq $s }).id }

# 4. Technicians
Save "technicians" @{ firstName="Eric";  lastName="Mugisha";   email="eric@auca.ac.rw";
  location=@{id=(LocId 1)}; expertises=@(@{id=(ExpId "Routing")}, @{id=(ExpId "Wireless")}) }
Save "technicians" @{ firstName="Grace"; lastName="Uwimana";   email="grace@auca.ac.rw";
  location=@{id=(LocId 2)}; expertises=@(@{id=(ExpId "Cabling")}) }
Save "technicians" @{ firstName="Jean";  lastName="Niyonzima"; email="jean@auca.ac.rw";
  location=@{id=(LocId 3)}; expertises=@(@{id=(ExpId "Wireless")}) }
$tech = Invoke-RestMethod "$base/technicians/all"
function TechId($e) { ($tech | Where-Object { $_.email -eq $e }).id }

# 5. Fault tickets: date, status, requester staffNumber, technician email, description, priority
$tickets = @(
  @("2026-10-05","RESOLVED",     "ST-1021","eric@auca.ac.rw",  "Core switch down in lab 3",      "HIGH"),
  @("2026-10-12","RESOLVED",     "ST-1021","grace@auca.ac.rw", "Faulty patch cable in office 2", "MEDIUM"),
  @("2026-10-20","INVESTIGATING","ST-1022","eric@auca.ac.rw",  "Intermittent Wi-Fi in library",  "HIGH"),
  @("2026-10-20","CANCELLED",    "ST-1023","eric@auca.ac.rw",  "Router reboot request",          "LOW"),
  @("2026-10-25","CANCELLED",    "ST-1024","grace@auca.ac.rw", "Cabling inspection",             "LOW"),
  @("2026-11-03","OPEN",         "ST-1021","jean@auca.ac.rw",  "Access point offline",           "MEDIUM"),
  @("2026-11-10","OPEN",         "ST-1025","grace@auca.ac.rw", "Slow network in registry",       "MEDIUM"),
  @("2026-11-15","INVESTIGATING","ST-1022","jean@auca.ac.rw",  "VPN connection drops",           "HIGH"),
  @("2026-11-20","OPEN",         "ST-1023","eric@auca.ac.rw",  "Routing table misconfiguration", "HIGH"),
  @("2026-11-28","OPEN",         "ST-1024","jean@auca.ac.rw",  "Wireless coverage gap",          "LOW")
)
foreach ($t in $tickets) {
  Save "fault-tickets" @{
    reportDate  = $t[0]; status = $t[1]; description = $t[4]; priority = $t[5]
    requester   = @{ id = (ReqId $t[2]) }
    technician  = @{ id = (TechId $t[3]) }
  }
}
"Seed data entered."