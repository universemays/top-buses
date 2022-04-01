import { HStack, Text } from "@chakra-ui/react";

export default function BusItem({ isExpanded, busRange, busNumber, numberOfBusStop }) {
  return (
    <HStack flex="1" spacing="24px" align={"center"}>
      <Text
        fontFamily={"heading"}
        fontSize={"2xl"}
        fontWeight={"bold"}
        color={isExpanded ? "white" : "green.400"}
        maxWidth={"48px"}
        width={"15%"}
      >
        {busRange}
      </Text>
      <HStack flex={"1"} px={2} spacing={2} align={"baseline"}>
        <Text fontSize={"3xl"} fontWeight={600} color={isExpanded ? "white" : "gray.600"}>
          {busNumber}
        </Text>
      </HStack>
      <HStack px={2} spacing={2} align={"center"}>
        <Text fontWeight={600} color={isExpanded ? "white" : "gray.600"}>
          {numberOfBusStop}
        </Text>
        <Text fontSize={"sm"} color={isExpanded ? "white" : "gray.500"}>
          stops
        </Text>
      </HStack>
    </HStack>
  );
}
