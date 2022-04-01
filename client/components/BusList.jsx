import {
    Box,
    Container,
    SimpleGrid,
    Stack,
    Accordion,
    AccordionItem,
    AccordionButton,
    AccordionPanel,
    AccordionIcon,
  } from "@chakra-ui/react";
  import BusItem from "./BusItem";
  import BusStopList from "./BusStopList";
  
  export default function BusList({ buses }) {
    return (
      <Container maxW={"6xl"} p={8}>
        <Accordion allowMultiple allowToggle>
          <SimpleGrid columns={{ base: 1, md: 2 }} spacing={6}>
            {buses.map((bus, index) => (
              <Stack key={bus.number}>
                <AccordionItem borderTop="hidden" borderBottom="hidden">
                  {({ isExpanded }) => (
                    <Box p={2} shadow="sm" borderWidth="1px" flex="1" borderRadius="lg">
                      <AccordionButton
                        _focus={{ boxShadow: "none" }}
                        _expanded={{ bg: "green.400", color: "white" }}
                        borderRadius="lg"
                      >
                        <BusItem
                          isExpanded={isExpanded}
                          busRange={index + 1}
                          busNumber={bus.number}
                          numberOfBusStop={bus.stops.length}
                        />
                        <AccordionIcon fontSize={"2xl"} color={isExpanded ? "white" : "gray.400"} />
                      </AccordionButton>
                      <AccordionPanel pt={4} pb={2}>
                        <BusStopList busStops={bus.stops} />
                      </AccordionPanel>
                    </Box>
                  )}
                </AccordionItem>
              </Stack>
            ))}
          </SimpleGrid>
        </Accordion>
      </Container>
    );
  }
  